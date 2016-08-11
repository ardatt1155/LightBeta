
import java.text.SimpleDateFormat
import scala.util.{Try, Success, Failure}

val begin = System.currentTimeMillis

val dnd = sc.textFile("./data/donotcall.txt").
	filter(x => x.filter(c => c.isDigit).length == 10).
	map(x => x.filter(c => c.isDigit))

val blocked = collection.SortedSet(dnd.collect() : _*)

val transactions = sc.textFile("./data/transactions.txt").
	map(x => x.split(";")).
	filter(x => x.length > 2 && x(1).trim()(0) == '$' && Try(x(1).trim().substring(1).toFloat).isSuccess && Try((new SimpleDateFormat("yyyy-mm-dd")).parse(x(2))).isSuccess).
	filter(x => x(2).startsWith("2015-")).
	map(x => (x(0), x(1).substring(1))).
	aggregateByKey(0)((r: Int, n: String) => r + (n.toFloat * 100).toInt, (r1: Int, r2: Int) => r1 + r2)

val users = sc.textFile("./data/users.txt").
	map(x => x.trim().split(";")).
	filter(x => x.length > 3 && x(0).trim().length() > 0).
	map(x => (x(0), x(1), x(2), x(3).split(",").filter(x => x.forall(c => c.isDigit || c == '(' || c == ')' || c == '-' || c == ' ') && x.filter(c => c.isDigit).length == 10).map(x => x.filter(c => c.isDigit))))

val reachables = users.
	map(x => (x._1, (x._2, x._3, x._4.filter(t => !blocked.contains(t))))).
	filter(x => x._2._3.length > 0)

val result = reachables.join(transactions).
	map(x => (x._2._2, (x._1, x._2))).
	sortByKey(false).
	map(x => (x._2._1, x._2._2._1._1, x._2._2._1._2, x._2._2._1._3, x._1)).
	map(x => x._1 + "; " + x._2 + "; " + x._4.map(p => "(" + p.substring(0, 3) + ") " + p.substring(3, 6) + "-" + p.substring(6)).mkString(", ") + "; $" + (x._5.toFloat / 100))

result.saveAsTextFile("result")

val end = System.currentTimeMillis

"Spark job took " + (end - begin) / 1000 + " seconds."

exit

