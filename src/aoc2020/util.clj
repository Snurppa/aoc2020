(ns aoc2020.util
  (:require [clojure.java.io :as io]))

(defn input-as-lines [file]
  (with-open [rdr (->> file
                       (str "inputs/")
                       (io/resource)
                       (io/reader))]
    ; hörp file into memory by forcing realization
    (doall (line-seq rdr))))

(comment 
  (input-as-lines "day1.txt")
  )
