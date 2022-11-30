(defproject dev.gethop/duct.module.cljs-compiler "0.1.1-SNAPSHOT"
  :description "Duct module to configure a ClojureScript compiler."
  :url "https://github.com/gethop-dev/duct.module.cljs-compiler"
  :license {:name "Mozilla Public Licence 2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [duct/compiler.cljs "0.3.0"]
                 [dev.gethop/duct.server.figwheel-main "0.1.4"]
                 [duct/core "0.8.0"]
                 [integrant "0.8.0"]]
  :deploy-repositories [["snapshots" {:url "https://clojars.org/repo"
                                      :username :env/clojars_username
                                      :password :env/clojars_password
                                      :sign-releases false}]
                        ["releases"  {:url "https://clojars.org/repo"
                                      :username :env/clojars_username
                                      :password :env/clojars_password
                                      :sign-releases false}]]
  :profiles
  {:dev          [:project/dev :profiles/dev]
   :profiles/dev {}
   :project/dev  {:plugins [[lein-cljfmt "0.8.0"]
                            [jonase/eastwood "1.3.0"]]}})
