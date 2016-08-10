
val dnd = sc.textFile("./data/donotcall.txt").map(x => (x, "DND")).map(x => (x._2, x._1)).groupByKey().map(x => collection.SortedSet(x._2.toList : _*))

val transactions = sc.textFile("./data/transactions.txt").map(x => x.split(";")).filter(x => x.length > 2 && x(2).contains("2015-")).map(x => (x(0), x(1).substring(1))).aggregateByKey(0)((r: Int, n:String) => r + (n.toFloat * 100).toInt, (r1: Int, r2: Int) => r1 + r2)

val users = sc.textFile("./data/users.txt").map(x => x.split(";")).filter(x => x.length > 3 && x(0).trim().length() > 0).map(x => (x(0), x(1), x(2), x(3).split(",")))

val reachables = users.cartesian(dnd).map(x => (x._1._1, (x._1._2, x._1._3, x._1._4.filter(t => !x._2.contains(t))))).filter(x => x._2._3.length > 0)

val result = reachables.join(transactions).map(x => (x._2._2, (x._1, x._2))).sortByKey(false).map(x => (x._2._1, x._2._2._1._1, x._2._2._1._2, x._2._2._1._3, x._1)).map(x => x._1 + "; " + x._2 + "; " + x._4.mkString(", ") + "; $" + (x._5.toFloat / 100))

result.saveAsTextFile("result")

exit


