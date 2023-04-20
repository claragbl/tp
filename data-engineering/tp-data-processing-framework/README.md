# Practices - Data engineering

## TP - Data processing with Apache Spark
To process a large amount of data partitioned on a data lake, you can use data processing frameworks such as Apache Spark :
1. Read : https://spark.apache.org/docs/latest/sql-programming-guide.html

Some questions : 

**Apache Spark** is a data processing framework that is able to perform processing task efficiently on large datasets. It 
provides API in different programming languages.  
**Spark SQL** provide Spark with more information about the structure of both the data and the computation being performed

* <u>What is Spark RDD API ?</u>    
RDD stands for **Resilient Distributed Dataset** and it's a concept at the hear of Apache Spark that  that represents an 
immutable collection of objects that can be split across a computing cluster. The Spark RDD API allows us to perform various 
transformations and actions on those RDD for advanced data processing. 

* <u>What is Spark Dataset API ?</u>  
The Spark Dataset API is a distributed collection of data that provides the benefits of both RDDs and DataFrames in Apache Spark.
Thanks to this API, we have a stongly-typed interface (for data manipulation )that allows for example to be sure that there is no typo in a sql query 
and to be sure that column names and data types are correct and consistent with the data structure.

* <u>With which languages can you use Spark ?</u>    
-> scala  
-> python   
-> R  
-> Java   
-> SQL  

* <u>Which data sources or data sinks can Spark work with ?</u>    
  (Data Sink = récepteur de données)  
Apache spark allows user to read data from various sources and it allows to write them in 
various destinations.   
-> Databases  
-> File systems   
-> Data lakes   
-> Message brokers: software component or service that facilitates communication between different applications or systems by allowing them to send and receive messages asynchronously.    
-> Streaming sources : data from various sources in real-time or near real-time, enabling processing of continuous data streams.

### Analyse data with Apache Spark and Scala 
One engineering team of your company created for you a TV News data stored as JSON inside the folder `data-news-json/`.

Your goal is to analyze it with your savoir-faire, enrich it with metadata, and store it as [a column-oriented format](https://parquet.apache.org/).

1. Look at `src/main/scala/com/github/polomarcus/main/Main.scala` and update the code 

**Important note:** As you work for a top-notch software company following world-class practices, and you care about your project quality, you'll write a test for every function you write.

You can see tests inside `src/test/scala/` and run them with `sbt test`

### How can you deploy your app to a cluster of machines ?
* https://spark.apache.org/docs/latest/cluster-overview.html

### Business Intelligence (BI)
How could use we Spark to display data on a BI tool such as [Metabase](https://www.metabase.com/) ?

Tips: https://github.com/polomarcus/television-news-analyser#spin-up-1-postgres-metabase-nginxand-load-data-to-pg

### Continuous build and test
**Pro Tips** : https://www.scala-sbt.org/1.x/docs/Running.html#Continuous+build+and+test

Make a command run when one or more source files change by prefixing the command with ~. For example, in sbt shell try:
```bash
sbt
> ~ testQuick
```