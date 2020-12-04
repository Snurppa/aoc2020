(ns aoc2020.day2
  (:require [aoc2020.util :as u]
            [clojure.string :as s]))

(defn parse-rule [rule]
  (let [space-idx (s/index-of rule \space)
        min-max   (subs rule 0 space-idx)
        [min max] (s/split min-max #"-")]
    {:min  (Integer/parseInt min) 
     :max  (Integer/parseInt max)
     :char (last rule)}))


(defn parse-row [row]
  (let [idx (s/index-of row \:)
        [rule pw] ((juxt #(subs % 0 idx)
                         #(subs % (+ idx 2))) ; colon+whitespace
                   row)]
    (assoc (parse-rule rule) :pw pw)))

(defn valid-pw? [{:keys [min max char pw]}]
  (let [freq (frequencies pw)
        char-count (get freq char)]
    (and char-count
         (<= char-count max)
         (>= char-count min))))

(defn part1 []
  (let [input (u/input-as-lines "day2.txt")]
    (->> input
         (map parse-row)
         (filter valid-pw?)
         (count))))

(defn part2 [])
