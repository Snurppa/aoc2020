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

(defn day6-input []
  (letfn [(read-groups [rdr]
            (loop [groups []
                   current []]
              (if-let [line (.readLine rdr)]
                (if (s/blank? line)
                  (recur (conj groups current) [])
                  (recur groups (conj current line)))
                (conj groups current))))]
    (with-open [rdr (->> "inputs/day6.txt"
                         (io/resource)
                         (io/reader))]
      (read-groups rdr))))
