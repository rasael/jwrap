## JWrap Documentation

* [Arrays](docs/ARRAY.md)

**Stream**

You can stream an array using `stream()`
```
var stream = $(array).stream();
```

This returns a **wrapped** stream, with is **enhanced** with additional methods.

You can also get the plain Stream :
```
var stream = $(array).pureStream(); // same as $(array).stream().get();
```

You also get certain convenience methods that are Stream related: `toList()`, `toSet()`.
```
var set = $(array).toSet();
```
### String
