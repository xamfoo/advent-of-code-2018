(ns xamfoo.cli
  (:require
    [clojure.string]
    ["fs" :as fs]
    ["process" :as process]
    ["yargs" :as yargs]))

(defn read-file []
  (let
    [file-path
     (first
       (-> (yargs/usage "Usage: $0 <file>")
           (.example "$0 input.txt")
           (.demandCommand 1)
           (.-argv._)))]
    (when (not (fs/existsSync file-path))
      (throw (js/Error. (str "Unable to read file \"" file-path "\""))))
    (clojure.string/split-lines
      (fs/readFileSync file-path "utf8"))))
