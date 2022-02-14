# jwrap - Predicate Wrapping Cheatsheet

## Common Operations
For the full documentation, please refer to the JavaDoc.

This page aims to a visual overview of the existing features:

###Testing multiple values
```
if ($(String::isBlank).accepts("", " ", "\n")) {
  ...
}

Iterable<String> strings = ...
if ($(String::isBlank).acceptsAll(strings)) {
  console.log("you provided only blank strings");
}
```

###Negation
```
 $(predicate).not();
```

###Removing entries from collection
```
var stringList = Arrays.asList("a","","b");
$(String::isEmpty).remove(stringList);

console.log(stringList);
// [a, b]
```

###Filtering an iterable
```
Iterable<String> strings = Arrays.asList("a","","b");
var newList = $(String::isEmpty).filter(stringList);

console.log(newList);
// [a, b]
```

###Nullability checks
If the predicate is nullable then in order to test it you must provide a default value using `orTrue()` or `orFalse()`
```
Predicate<String> predicate = null;
var result = $(predicate).orFalse()
                         .test("Hello");

console.log(result);
// false

result = $(predicate).orTrue()
                     .test("Hello");

console.log(result);
// true
```
###Setting the predicate to null
```
Predicate<String> predicate = String::isBlank;

predicate = $(predicate).whenAccepts(" ");
predicate.test("");
// true

predicate = $(predicate).whenAccepts("a");
predicate.test("");
// false
```