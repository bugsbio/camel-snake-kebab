(ns camel-snake-kebab.core-test
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.internals.misc :refer [split]]
            #+clj  [clojure.test :refer :all]
            #+cljs [cemerick.cljs.test :as t])
  #+cljs (:require-macros [cemerick.cljs.test :refer [deftest is testing are]])
  #+clj  (:import (clojure.lang ExceptionInfo)))

#+clj
(deftest split-test
  (are [x y] (= x (split y))
    ["foo" "bar"] "foo bar"
    ["foo" "bar"] "foo\n\tbar"
    ["foo" "bar"] "foo-bar"
    ["foo" "Bar"] "fooBar"
    ["Foo" "Bar"] "FooBar"
    ["foo" "bar"] "foo_bar"
    ["FOO" "BAR"] "FOO_BAR"
    
    ["räksmörgås"] "räksmörgås"
    
    ["IP" "Address"] "IPAddress"))
    
(def zip (partial map vector))

(deftest format-case-test
  (testing "examples"
    (are [x y] (= x y)
      'FluxCapacitor  (csk/camel-case 'flux-capacitor)
      "i_am_constant" (csk/snake-case "I am constant")
      :object-id      (csk/kebab-case :object_id)
      "X-SSL-Cipher"  (csk/http-header-case "x-ssl-cipher")))

  (testing "rejection of namespaced keywords and symbols"
    (is (thrown? ExceptionInfo (csk/camel-case (keyword "a" "b"))))
    (is (thrown? ExceptionInfo (csk/camel-case (symbol  "a" "b")))))

  (testing "all the type preserving functions"
    (let
      [inputs    ["FooBar"
                  "foo_bar"
                  "foo-bar"
                  "Foo_Bar"]
       functions [csk/camel-case
                  csk/snake-case
                  csk/kebab-case
                  csk/camel-snake-case]
       formats   [identity keyword symbol]]

      (doseq [input inputs, format formats, [output function] (zip inputs functions)]
        (is (= (format output) (function (format input))))))))

(deftest http-header-case-test
  (are [x y] (= x (csk/http-header-case y))
    "User-Agent"       "user-agent"
    "DNT"              "dnt"
    "Remote-IP"        "remote-ip"
    "TE"               "te"
    "UA-CPU"           "ua-cpu"
    "X-SSL-Cipher"     "x-ssl-cipher"
    "X-WAP-Profile"    "x-wap-profile"
    "X-XSS-Protection" "x-xss-protection"))
