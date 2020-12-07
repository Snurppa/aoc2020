(ns aoc2020.util
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(defn input-as-lines [file]
  (with-open [rdr (->> file
                       (str "inputs/")
                       (io/resource)
                       (io/reader))]
    ; hörp file into memory by forcing realization
    (doall (line-seq rdr))))


(defn passport-day4-input [file]
  (letfn [(get-passports [rdr]
            (loop [passports []
                   current ""]
              (if-let [line (.readLine rdr)]
                (if (s/blank? line)
                  (recur (conj passports current) "")
                  (recur passports (str current " " line)))
                (conj passports current))))]
    (with-open [rdr (->> file
                         (str "inputs/")
                         (io/resource)
                         (io/reader))]
      (map #(subs % 1) (get-passports rdr)))))
