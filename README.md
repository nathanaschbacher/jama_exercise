__nathanaschbacher/jama_exercise__

#Overview

##The Problem:

A supermarket sells 3 products listed below: 

  1. Product A = $20
  2. Product B = $50 (or 5 for the price of 3)
  3. Product C = $30

Implement the code for a checkout register that calculates the price of a given sequence of items. The input is a product list as a String, e.g “ABBACBBAB” : for which the output should be the integer 240.

Implement the following:

```
public class Supermarket {
    public int checkout(String items);
}
```

**Prerequisites:**
* Java SDK
* Maven

#Usage

##Installation

**Checkout project into your working directory**

    $ cd ~/Documents
    $ git clone https://github.com/nathanaschbacher/jama_exercise.git

##Running

    $ cd ~/Documents/jama_exercise
    $ mvn package
    $ java -cp target/jama_exercise-1.0-SNAPSHOT-jar-with-dependencies.jar aschbacher.jacob.nathan.Supermarket

##Testing

    $ cd ~/Documents/jama_exercise
    $ mvn test

#Valuable Lessons Learned

  1. When you finally give up and just do the problem it takes about 45 minutes.
  2. Akka is a trainwreck compared to OTP in terms of simplicity and flexibility even on Scala.
    1. If you're using Akka with Java, you must be like me and love punishing yourself.
  3. The Erlang version of the fun Actor network version of this problem is trivially easy.
    2. I think I might make that the next Erlang/Elixir Games problem.
