# What is BabyFish?

BabyFish is an Object Association Management framework(Let's call it OAM), it can be split to 2 parts. 

### 1. A Java framework for data model classes.

Generally speaking, program can be split to three layers: data accessing layer, business logic layer and Presentation layer, but there are some data model classes that do not belong to none of those layers, they are standalone and shared by every layer. Often, people call it "Entity Classes" too.

It's easy for developer to create a simple class without associations, but it's hard for developer to create complex object graph with bidirectional associations. BabyFish help developer to to create powerful object graph with auto synchroization bidirectional associations fastly with a little code. 

There are many technologies for those three layers, but unfortunately, the data model classes that are shared by every layer are often neglected, people often think writing some C-structure style classes with some getter and setter is enough. BabyFish tries to try to change this phenomenon, it is a framework of data model classes.

In order to support powerful data model developing, babyfish supports two core functionalities

* BabyFish Collection Framework(X Collection Framework + MA Collection Framework).
* ObjectModel(ObjectModel4Java + ObjectMode4JPA).

### 2. An enhancement of JPA/Hibernate.

In order to adapt to the new data model technology, of course, every classic layer needs to be improved. For first version, it only improved the data accessing layer because the time is not enough.

In the first version, JPA/Hibernate is enhanced( BabyFish-1.0.0 Alpha is used to enhance JPA2.1/Hibernate-4.3.6.Final), let's call it BabyFish-JPA/BabyFish-Hibernate. it also merges some advantages of ADO.NET Entity Framework into JPA/Hibernate.

BabyFish-JPA/BabyFish-Hibernate supports these three core functionalities

* BabyFish JPA Criteria
* Query Path
* Distinct Limit Query

> By the way, "BabyFish" is a Chinglish word, it means "Andrias davidianus". Its voice sounds like the baby's cry, that why Chinese call it "BabyFish". ^ω^

# Functionalities
#### A. Java Part
* Event combiner: support .NET style event notification mechanism.
* Typed-18N: support strong type "18N" which can report the I18N errors at compilation time.
* X-Collection-Framework: Enhance the Java Collection Framework. The most important functionality is "Unstable Collection Elements", element/key can be modified after it has been added into set(map) because the corresponding sets/maps will be adjusted automatically when it's changed.
* MA-Collection-Framework: Enhance the X-Collection-Framework to let collections support modification notification like the trigger of database. The most important functionality is "Bubbled Event", If the modification event has been triggered by iterator of view(eg: List.subList, SortedMap.subMap), it will bubbled to its parent view, util the original root collection triggered the event.
* ObjectModel4Java: A powerful API to create Java model classes, it supports the bidirectional association between objects. When one side bidirectional association is changed, the other side will be notified and adjusted automatically. User only need to declared some annotations and all the functionalities will be implemented by the byte-code generated dynamically in runtime.

#### B. ORM Part
* ObjectMode4JPA: Enhance ObjectModel4Java. let ObjectModel support JPA entity classes.
>* Replace the lazy proxy and lazy collection of Hibernate to support ObjectModel with laziness management.
>* Support Maven plugin to change the byte-code of entity class at compilation time, programmer only need to write the simple JPA entity classes, and complex code for the ObjectMode4JPA can be added into entity classes automatically during compilation.

* BabyFish-JPA-Criteria: A smarter implementation of JPA Criteria, it supports more functionalities, and it can optimize the generated JPQL.
* QueryPath: Its source code is generated by maven plugin at compilation time so that all the errors can be report at compilation time. It can be decided by UI Layer dynamically, then dispatch it to Business Logic Layer, and finally dispatch it to the Data Access Layer. In Data Access Layer, it can
>* Fetch lazy associations dynamically with any depth and breadth
>* Fetch lazy scalar associations(eg: Lob) dynamically
>* Sort the query result or collection association dynamically with any depth and breadth

* DistinctLimiQuery: Enhance the Oracle Dialect of Hibernate to resolve a problem of Hibernate, In hibernate, when paging query(with firstResult/maxResults) contains collection fetches, hibernate has to query all the data and do the paging filter in memory. This functionality can resolve this problem when database is Oracle.

# How to run and learn it
* Please view get-started.html to install and run it. 
* Please view demo/demo-guide.html to learn it, that document describe every demo and give a suggestion for learning order. 

> Fastest way to learn it
> 
> This framework is big so it need some time to know all the functionalities. If the time or interest is not enough so that you want to know only the most important functionalities of BabyFish in least possible time. You can learn 4 UnitTest classes 
>* ${babyfish-dir}/demo/babyfishdemo-om4java/src/test/java/org/babyfishdemo/om4java/l2ir/ObjectModelOfListAndIndexedReferenceTest.java
>* ${babyfish-dir}/demo/babyfishdemo-spring/src/test/java/org/babyfishdemo/spring/dal/QueryPathTest.java
>* ${babyfish-dir}/demo/babyfishdemo-xcollection/src/test/java/org/babyfishdemo/xcollection/uce/UnstableCollectionElementsTest.java
>* ${babyfish-dir}/demo/babyfishdemo-macollection/src/test/java/org/babyfishdemo/macollection/bubble/SimpleBubbleEventTest.java

# Why did the first version cost so long time(2008-2015)?
I started to develop this framework since Aug, 2008, the first version is coming so late because
* I use my spare time to develop this framework, because I have to spend most of my time to engage in some boring works for salary to support myself.
* The developping had been suspended for more than 2 years because of private affairs.
* After I finished the main projects developing at the end of 2013, I need to create demo and deocument by english, that cost me so much time because my English is not good.

# license: LPGL3.0
BabyFish uses the LGPL-3.0 license so that it can be used in commercial projects, 
please see [http://opensource.org/licenses/LGPL-3.0](http://opensource.org/licenses/LGPL-3.0) to know more.

# Thanks
Thank two great frameworks: [ASM](http://asm.ow2.org) and [ANTLR](http://www.antlr.org)

# Video resources

> Chinsese videos:
>* [http://v.youku.com/v_show/id_XMTM3MjEyNDExNg==](http://v.youku.com/v_show/id_XMTM3MjEyNDExNg==)  
>* To be continue.

> English videos: Will be added soon. 

# About me
Tao Chen(陈涛), [babyfish-ct@163.com](mailto:babyfish-ct@163.com)

2015-10-11, ChengDu, China