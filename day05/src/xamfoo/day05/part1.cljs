(ns xamfoo.day05.part1
  (:require
    [xamfoo.day05.util :as util]))

(defn stable-len [[polymer]] (count (util/reduce-polymer polymer)))
