# jwrap - String Wrapping Cheatsheet

## Common Operations
For the full documentation, please refer to the JavaDoc.

This page aims to a visual overview of the existing features:

### Wrapping
```
var wrapped = $("H1ello World");
```

### Long Literal Strings
```
var text = $("""
    This is a very long string which needs
    to wrap across multiple lines because
    otherwise my code is unreadable.""");
```
### Character access
```
$("dog").charAt(1);
// 'o'
```
### JavaScript like Character access

```
$("dog").at(-1);
// "g"
```
### Comparing strings
```
$("cat").isEqualTo("dog");
// false
```