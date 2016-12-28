#Family Tree
###Setup
1. In your program (e.g. IntelliJ), install the following plugins: Maven, JUnit, Cucumber for Java
2. Setup Maven in your program using the pom.xml. In IntelliJ go to View - Tool Windows - Maven Project, to open the Maven window. Click on + and choose the pom.xml
3. Note: You may want to select 1.8 as module SDK in the project structure in IntelliJ

###Run a test
Right click on FamilyTreeTest.feature, click on Run Feature: FamilyTreeTest

###Usage
```
cd to/location/of/familytree.jar
java -jar familytree.jar
``` 

You will be presented with a simple command prompt:
`>`

**Names are not allowed to contain spaces.**

The following commands are supported:
```
INSERT PERSON <name> <gender>
```
Adds a person with name `name` and the given gender (MALE or FEMALE).

```
MAKE <name-of-child> CHILD OF <name-of-parent>
```

```
MARRY <name1> WITH <name2>
```

```
PRINT TREE
```
Prints the complete family tree.

```
RESET
```

Resets the tree to an empty state.

```
GET <Relation> OF <name>
```
Querys the tree and returns a set.

Possible values for `Relation` are:
`Spouse`, `Parents`, `Children`, 
`Siblings`, `Grandparents`, `Grandchildren`, `Cousins`,
`Fathers_Siblings`, `Mothers_Siblings`.

```
IS <name1> <Relation> OF <name2>
```
Querys the tree and answers either _YES_ or _NO_.

Possible values for `Relation` are:
`Spouse`, `Parent`, `Child`,
`Sibling`, `Grandparent`, `Grandchild`, `Cousin`,
`Fathers_Sibling`, `Mothers_Sibling`.