(ns aoc2020.day6
  (:require [aoc2020.util :as u]))


(defn group-answers [group]
  (set (reduce str group)))

(defn part1 []
  (let [answers (u/day6-input)]
    (->> (map group-answers answers)
         (map count)
         (reduce + 0))))

(defn part2 [])
