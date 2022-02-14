# jwrap
### Java Convenience Operator

[![Java CI with Maven](https://github.com/rasael/jwrap/actions/workflows/maven.yml/badge.svg)](https://github.com/rasael/jwrap/actions/workflows/maven.yml)
[![Maven Package](https://github.com/rasael/jwrap/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/rasael/jwrap/actions/workflows/maven-publish.yml)

## Table of Contents
* [General info](#general-info)
* [Dependencies](#dependencies)
* [Setup](#setup)
* [Additional Info](#additional-info)

## General Info

`jwrap` (or 'the `$`' operator) is a convenience API for Java applications which
aims to enhance your plain java objects.

Using the operation you *wrap* an Object and gain additional methods which are usually only accessible via utilities.

## Key Features
* Enhancement of any runtime Java object
* Arrays JavaScript-like syntax
* Stream, Collections 'enhanced' syntax
* Facade for several utility libraries
* Favor a more readable syntax

## Code Example

Using `jwrap` its immediate, just import it, and you're ready to go:

```
import static net.bervini.rasael.jwrap.JWrap.$;

var number = 42;
if ($(number).isEven()) {
  ...
}
```

Or with an array

```
var someArray = new String[]{"c","a","b"};

$(someArray).swap(1,2)
            .reverse()
            .forEach(System.out::println);
```

Or add those missing methods to `Stream`:

```
var list = Arrays.asList('a', null, 'b','1','2');

$(list).stream()
       .removeNulls()
       .remove(Character::isNumber)
       .forEach(System.out::println);
```

## Dependencies

Currently `jwrap` internally uses:

- Apache commons-lang 3.12.0
- Apache commons-text 1.9
- Apache commons-collections4 4.4
- Gson 2.8.9 (for the `.json()` calls)

## Setup

To empower your Java objects


### Why to try jwrap

### Too many utilities, one more?

`jwrap` is also a *facade* for common utilities;
therefore it can cover what other API can offer, but with a more fluent syntax.

For example:

```
$(name).startsWithIgnoreCase("ras")
```

This internally uses `Apache Common Lang`, which *should* reduce chances for bugs.

### I often need json

Good, then `jwrap` offers you `json()` method to quickly transform any java object to its Json representation.

```
Person person = ...
String jsonStr = $(person).json();
```

### Yet another import and dependency

In most use cases, `jwrap` requires only a static import

```
import static net.bervini.rasael.jwrap.JWrap.$
```

(or `JWrap.Wrap` if you prefer that fashion)

That's it, for all the rest of uses you can do with `var`.

`jwrap` **will** import transitive dependencies to your project, so if you are working with another version of a library used by jwrap, you may need to micro-manage the version in the pom file.
This is normal with practically **all** libraries.

### It offers a different syntax phrasing

`jwrap` can help code readability, reducing null checks (it always acts as a `java.util.Optional`) and moving 
the `verb` nearer the `subject` of a line of code.

```
// Standard utility call, subject and other are declared at the end
if (StringUtils.startsWithIgnoreCase(string, "SomeOtherString")) {
 ...
}

// jwrap syntax allows to start with the subject
if ($(string).startsWithIgnoreCase("SomeOtherString")) {
  ...
}
```

Often it also supports inversion of control, which can help readability

```
if ($("SomeOtherString").isStartOf(string)) {
  ..
}
```

## When to use jwrap

### Prototyping

This API is works very well for prototyping applications since you only require one dependency to already enhance a
lot your code.


## Additional Info

> Additional documentation can be found [here](DOCS.md).

## You may also like..


* [assert-j](http://joel-costigliola.github.io/assertj/) - fluent assertion API

## Authors

> This project it's written by `Rasael Bervini`
 
## License

> Apache License 2.0