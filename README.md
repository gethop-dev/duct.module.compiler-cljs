[![Build Status](https://github.com/gethop-dev/duct.module.compiler-cljs/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/gethop-dev/duct.module.cljs-compiler/actions/workflows/ci-cd.yml)
[![Clojars Project](https://img.shields.io/clojars/v/dev.gethop/duct.module.cljs-compiler.svg)](https://clojars.org/dev.gethop/duct.module.cljs-compiler)
# duct.module.cljs-compiler

[Duct](https://github.com/duct-framework/duct) module for configuring a ClojureScript compiler.

## Installation

[![Clojars Project](https://clojars.org/dev.gethop/duct.module.cljs-compiler/latest-version.svg)](https://clojars.org/dev.gethop/duct.module.cljs-compiler)

## Usage

After adding the dependency add the following key to your `config.edn`
module configuration:

``` edn
:dev.gethop.duct.module/cljs-compiler {}
```

The module assumes that the main entrypoint for ClojureScript
application which is `<project-ns>.client`. But it's configurable.
The module accepts a `:environments` key which is a map with multiple
environments configuration (dev, test, prod, etc.).  Each environment
has a mandatory key `:compiler` and a optional key
`:compiler-config`. The former is the compiler you want to use for the
given environment, and the latter is the configuration for the compiler.

Currently the module supports two compilers:

- [`figwheel-main`](https://github.com/bhauman/figwheel-main)
- [`closure-compiler`](https://clojurescript.org/reference/compiler-options)

The module already provides a default configuration that is sensible
enough for a development (using `figwheel`) and a production (using
`closure-compiler`).

Here is a sample configuration:

``` edn
:dev.gethop.duct.module/cljs-compiler
{:environments {:development {:compiler :figwheel-main
                              :compiler-config {:options {:main foo.client}}}
                :production {:compiler :closure-compiler
                             :compiler-options {:build-options {:main foo.client}}
```

Bear in mind that each `:compiler-config` is specific to the
`:compiler` technology. So please refer to their documentation to view
all the possible configuration options.

## License

Copyright (c) 2022 HOP Technologies.

The source code for the library is subject to the terms of the Mozilla
Public License, v. 2.0. If a copy of the MPL was not distributed with
this file, You can obtain one at https://mozilla.org/MPL/2.0/.
