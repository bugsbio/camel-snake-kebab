# camel-snake-kebab

A fork of @qerub's Clojure library for word case conversions.

## Motivation

We were having problems AOT compiling code on a Mac
(due to some function names that differ only in case, as described in the original readme) and
had a few key names that don't map neatly onto the original csk model (`:s3-key` for example),
so we decided it was easier to fork the project than deal with changing our project build
and having code to manage the exeptions. Thanks to @qerub for a great project though.


We've reduced and simplified the functions available to the following:

```clojure
camel-case
camel-snake-case
snake-case
kebab-case
http-header-case
```

There are no type converting functions (you get back the type you put in),
and nothing to uppercase or lower case a string. Numbers don't act as separators,
so `(snake-case :s3-key) ; => :s3_key`, and `(kebab-case "Adler123") => "adler123"`.
It's a tradeoff but it's the one that works for us.

Original repo and readme at https://github.com/qerub/camel-snake-kebab.

License

Copyright (C) 2012-2014 the AUTHORS.

Distributed under the Eclipse Public License, the same as Clojure.
