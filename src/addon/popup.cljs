(ns addon.popup
  (:require [reagent.dom :as rdom]
            [clojure.string]
            [addon.download :as download]
            [addon.browser :as browser]))

;; Local Components ;;
(defn download-option [label option-handler]
  [:button {:on-click option-handler} label])

(defn download-option-list [opts]
  [:ul (for [x opts] ^{:key x}
         [:li [download-option (:label x) (:option-handler x)]])])

(defn scanned-list [scanned-files]
  (letfn [[scanned-file [{:keys [ext url]}]
           [:dl [:dt "Extension"] [:dd ext]
                [:dt "URL"] [:dd url]]]]
    [:ul (for [f scanned-files] ^{:key f} [:li [scanned-file f]])]))


;; Popup Definition ;;
(def download-options
  [{:label "Download all files (.pdf, .docx, ...)" :fn download/all-files}
   {:label "Download all .pdf" :fn download/pdf}
   {:label "Download all .pptx" :fn download/powerpoint}
   {:label "Download all .doc(x)" :fn download/word}])

(defn popup []
  (let [url-parsed (browser/parse-url (js/window.location.href))
        technion? (true? url-parsed)
        scanned-files (browser/scan-files url-parsed)]

    [:div [:h1 "I'm alive!"]
     [:h2 "Download options"]
     (cond (not technion?) [:div "Not a Technion website"]
           (<= scanned-files 0) [:div "No (supported) files were found"]
           :else [download-option-list download-options])
     [:h2 "Scanned files:"]
     [scanned-list scanned-files]]))


(defn ^:export run []
  (rdom/render [popup] (js/document.getElementById "app")))
