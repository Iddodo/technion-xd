(ns addon.popup
  (:require [reagent.dom :as rdom]
            [clojure.string]
            [addon.technion :as technion]
            [addon.download :as download]
            [addon.browser :as browser]))

;; Local Components ;;
(defn download-option [label d-fn]
  [:button {:on-click d-fn} label])

(defn download-option-list [opts]
  [:ul (for [x opts] ^{:key x}
         [:li [download-option (:label x) (:fn x)]])])


(defn scanned-file [file]
  [:<> file])

(defn scanned-format-files [format]
  [:div [:h3 (:ext format)]
   [:ul (map #(vector :li scanned-file %) (:urls format))]])

(defn scanned-list [scanned-url]
  [:<> (for [format scanned-url]
         ^{:key format}
         [scanned-format-files format])])

;; Popup Definition ;;
(def download-options
  [{:label "Download all files (.pdf, .docx, ...)" :fn download/all-files}
   {:label "Download all .pdf" :fn download/pdf}
   {:label "Download all .pptx" :fn download/powerpoint}
   {:label "Download all .doc(x)" :fn download/word}])

;; (defn popup []
;;   [:div "I'm alive!"])

(defn popup []
  (let [;;url (.-href js/window.location)
        url "https://grades.cs.technion.ac.il/grades.cgi?zAYZOGmYYm9k0Ze+3+234129+202202+ho_9566001537257776.html"
        url-parsed (technion/parse-url url)
        technion? (some? url-parsed)
        scanned-url (browser/scan-url-formats (:supported-url url-parsed))
        files? (some? scanned-url)]

    [:div [:h1 "I'm alive!"]
     [:h2 "Download options"]
     (cond (not technion?) [:div "Not a Technion website"]
           (not files?) [:div "No (supported) files were found"]
           :else [download-option-list download-options])
     [:h2 "Scanned files:"]
     [scanned-list scanned-url]]))


(defn ^:export run []
  (rdom/render [popup] (js/document.getElementById "app")))
