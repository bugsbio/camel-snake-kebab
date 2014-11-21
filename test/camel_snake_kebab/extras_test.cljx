(ns camel-snake-kebab.extras-test
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :refer [transform-keys]]
            #+clj  [clojure.test :refer :all]
            #+cljs [cemerick.cljs.test :as t])
  #+cljs (:require-macros [cemerick.cljs.test :refer [deftest is testing are]]))
