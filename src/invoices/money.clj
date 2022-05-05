(ns invoices.money
  (:require
    [clojurewerkz.money.amounts :as ma]
    [clojurewerkz.money.currencies :as mc]))

(defn euros [n]
  (ma/amount-of mc/EUR n))

(defn multiply [n s]
  (ma/multiply n s :half-up))

(defn plus [n m]
  (ma/plus n m))

(defn minus [m n]
  (ma/minus m n))

(defn total [moneys]
  (ma/total moneys))


