(ns addon.download
  (:require
    [addon.db :as db]))

(defn fetch-files [files] files) ;; TODO

(defn fetch-filtered [fl filter-fn]
  (let [filtered-files (filter-fn fl)]
    (fetch-files filtered-files)))

(defn all-files [fl] (fetch-files fl))

(defn pdf [fl] (fetch-filtered fl #(= (:type %) :pdf)))
(defn powerpoint [fl] (fetch-filtered fl #(= (:type %) :ppt?x)))
(defn word [fl] (fetch-filtered fl #(= (:type %) :doc?x)))
