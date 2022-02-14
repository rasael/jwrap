# jwrap - Array Wrapping Cheatsheet

## Common Operations
For the full documentation, please refer to the JavaDoc.

This page aims to a visual overview of the existing features:

### Wrapping
```
var javaArray = new String[]{"Apple","Banana"};

var array = $(javaArray);
```
### Size of an array
```
console.log(array.size()); 
// 3
```
Null arrays always have a size of **0**.
### Representing an array
```
console.log(javaArray); 
// [Ljava.lang.String;@1f1c7bf6

out.println(array); 
// [1, 2, b]
```

### Access an Array item using the index position

```
var first = array.get(0);
// "Apple"
```

### Loop over an Array
```
array.forEach(console::log);
// "Apple"
// "Banana"
```
Or using `for-each`
```
for (var fruit : array) {
  console.log(fruit);
}
// "Apple"
// "Banana"
```
Or with an iterator
```
var iterator = $(array).iterator();

while (iterator.hasNext()) {
  var item = iterator.next();
}
```
Or with an indexed iterator
```
var array = new String[]{"a","b"};

$(array).forEachEntry((i,e) -> {
          console.log(i + ", " + e);
        });
// 0, a
// 1, b 
```
### Stream over an Array
```
array.stream()
     .mapToInt(String::length)
     .toList();
```
### Transforming to List or Set
```
var names = $(new String[]{"Joe","Rasael","Joe"});

var list = names.toList();
// [Joe, Rasael, Joe]

var set = names.toSet();
// [Joe, Rasael]
```

### Add an item to the end of an Array
```
fruits.push("Orange")
// ["Apple", "Banana", "Orange"]
```

### Remove an item from the end of an Array
```
var last = fruits.pop() // remove Orange (from the end)
// ["Apple", "Banana"]
```

### Remove an item from the beginning of an Array

```
var first = fruits.shift() // remove Apple from the front
// ["Banana"]
```
### Add an item to the beginning of an Array
```
fruits.unshift("Strawberry") // add to the front
// ["Strawberry", "Banana"]
```
### Finding an item using relative indices
```
console.log(array.at(-1));
// "Banana"
```
### Find the index of an item in the Array
```
fruits.push("Mango")
// ["Strawberry", "Banana", "Mango"]

var pos = fruits.indexOf("Banana")
// 1
```
### Remove an item by index position
```
fruits.splice(pos, 1) // this is how to remove an item

// ["Strawberry", "Mango"]
```

You can also obtain the removed item
```
var removed = Lists.<String>newList();

fruits.spliceTo(0, 1, removed)
// ["Mango"]

console.log(removed);
// "Strawberry" 
```


### Remove items from an index position
```
var javaVegiArray = new String[]{"Cabbage", "Turnip", "Radish", "Carrot"};
var vegetables = $(javaVegiArray);

console.log(vegetables);
// ["Cabbage", "Turnip", "Radish", "Carrot"]

var pos = 1;
var n = 2;

var removedItems = Lists.<String>newList();

vegetables.spliceTo(removedItems, pos, n);
// this is how to remove items, n defines the number of items to be removed,
// starting at the index position specified by pos and progressing toward the end of array.

console.log(vegetables)
// ["Cabbage", "Carrot"] (the original array is changed)

console.log(removedItems);
// ["Turnip", "Radish"]
```

### Removing items with a predicate
```
var array = {'a','b,'1','2','3'};

$(array).removeIf(Character::isNumber);
// [a, b]
```
or the opposite operation with `filter()`
```
var array = {'a','b,'1','2','3'};

$(array).filter(Character::isNumber);
// [1, 2, 3]
```
### Counting elements of an array
```
var array = {'a','b,'1','2','3'};
var count = $(array).count(Character::isNumber);

console.log(count);
// 3
```


