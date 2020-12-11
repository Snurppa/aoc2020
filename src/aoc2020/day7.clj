(ns aoc2020.day7
  (:require [aoc2020.util :as u]
            [clojure.set :as set]
            [clojure.string :as s]))

(def bag-pattern #"^\w+\s\w+")
(def contains-pattern #"\d+\s\w+\s\w+")

(defn parse-rule [rule-str]
  (let [bag (re-find bag-pattern rule-str)
        bags (->> (re-seq contains-pattern rule-str)
                  (map (fn [bag-count-str]
                         (let [[num bag] (s/split bag-count-str #"\s" 2)]
                           {:bag bag
                            :quantity (Integer/parseInt num)}))))]
    {:bag bag
     :contains bags}))

(defn the-loop [bags the-bag]
  (let [make-pred (fn [bag-set] (comp (partial contains? bag-set) :bag))
        get-candidates (fn [pred]
                         (->> bags
                              (filter #(some pred (second %)))
                              (map first)
                              (set)))]
    (loop [candidates (get-candidates (make-pred #{the-bag}))
           containers #{}]
      (if (not-empty candidates)
        (recur (get-candidates (make-pred candidates))
               (set/union containers candidates))
        containers))))

(defn part1 []
  (let [rules (->> (u/input-as-lines "day7.txt")
                   (map parse-rule))
        by-bag (zipmap (map :bag rules) 
                       (map :contains rules))]
    (the-loop by-bag "shiny gold")))
