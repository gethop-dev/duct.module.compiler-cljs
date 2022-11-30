(ns dev.gethop.duct.module.util
  (:require [clojure.string :as str]))

(defn project-ns
  [config]
  (get config :duct.core/project-ns))

(defn project-dirs
  [config]
  (-> (project-ns config)
      name
      (str/replace "-" "_")
      (str/replace "." "/")))

(defn get-environment
  [config options]
  (get options :environment (get config :duct.core/environment :production)))
