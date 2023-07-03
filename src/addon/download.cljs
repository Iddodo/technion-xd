(ns addon.download
  (:require
    [addon.popup.db :as db]))

(defn fetch-files [files] files) ;; TODO

(defn fetch-filtered [filter-fn]
  (let [filtered-files (filter filter-fn @db/scanned-files)]
    (fetch-files filtered-files)))

(defn all-files [] (fetch-files @db/scanned-files))

(defn pdf [] (fetch-filtered #(= (:type %) :pdf)))
(defn powerpoint [] (fetch-filtered #(= (:type %) :ppt?x)))
(defn word [] (fetch-filtered #(= (:type %) :doc?x)))
