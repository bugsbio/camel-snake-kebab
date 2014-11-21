(ns camel-snake-kebab.internals.macros
  (:require [camel-snake-kebab.internals.misc :refer [convert-case]]))

(defmacro defconversion [case-label first-fn rest-fn sep]
  `(let [convert-case# (partial convert-case ~first-fn ~rest-fn ~sep)]
     (defn ~(symbol case-label) [s#]
       (camel-snake-kebab.core/alter-name s# convert-case#))))
