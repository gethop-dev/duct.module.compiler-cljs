(ns dev.gethop.duct.module.cljs-compiler
  (:require [dev.gethop.duct.module.util :as util]
            [duct.core :as core]
            [duct.core.merge :as duct.merge]
            [integrant.core :as ig]))

(defmulti build-cljs-compiler-config
  (fn [_config options]
    (get options :compiler)))

(defmethod build-cljs-compiler-config :closure-compiler
  [config options]
  (let [project-ns (util/project-ns config)
        project-dirs (util/project-dirs config)]
    {:duct.compiler/cljs
     {:builds
      [(duct.merge/meta-merge
        {:source-paths ["src/"]
         :build-options
         {:main (symbol (str project-ns ".client"))
          :output-to (format "target/resources/%s/public/js/main.js" project-dirs)
          :output-dir (format "target/resources/%s/public/js" project-dirs)
          :asset-path "/js"
          :closure-defines {:goog.DEBUG false}
          :aot-cache true
          :parallel-build true
          :verbose true
          :optimizations :advanced}}
        (:compiler-config options))]}}))

(defmethod build-cljs-compiler-config :figwheel-main
  [config options]
  (let [project-ns (util/project-ns config)
        project-dirs (util/project-dirs config)]
    {:duct.server/figwheel-main
     (duct.merge/meta-merge
      {:id "dev"
       :options {:main (symbol (str project-ns ".client"))
                 :output-to (format "target/resources/%s/public/js/main.js" project-dirs)
                 :output-dir (format "target/resources/%s/public/js" project-dirs)
                 :asset-path "/js"
                 :verbose false
                 :optimizations :none
                 :parallel-build true}
       :config {:mode :serve
                :open-url false
                :ring-server-options {:port 3449 :host "0.0.0.0"}
                :css-dirs [(format "target/resources/%s/public/css" project-dirs)]}}
      (:compiler-config options))}))

(def ^:private ^:const default-module-options
  {:environments {:development {:compiler :fighweel-main}
                  :production {:compiler :closure-compiler}}})

(defmethod ig/init-key :dev.gethop.duct.module/cljs-compiler
  [_ options]
  (fn [config]
    (let [options (duct.merge/meta-merge default-module-options options)
          environment (util/get-environment config options)
          environment-config (get-in options [:environments environment])]
      (core/merge-configs config (build-cljs-compiler-config config environment-config)))))
