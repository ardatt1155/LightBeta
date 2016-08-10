#!/bin/bash
spark-shell -i ./solution/solution.scala
cat result/part-0000* | head -1000 > campaign.txt
rm -rf result*
rm -rf derby.log metastore_db
