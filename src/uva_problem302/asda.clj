(ns uva_problem302.asda)
(use '[uva_problem302.core :refer :all])

;(def facts
;  '([isa street1 street][isa street2 street]
;    [isa street3 street][isa street4 street]
;     [isa ?john john]
;     [at john street1]
;      [at john street2]
;     [junction street1 junction1]
;     [junction street1 junction2]
;     [junction street2 junction1]
;     [junction street2 junction4]
;     [junction street3 junction4]
;     [junction street3 junction3]
;     [junction street4 junction2]
;     [junction street4 junction3]))

;(def facts
;  '(
;     (junction1 junction1)
;     (junction2 junction2)
;     (street1 street1)
;     (street2 street2)))
;(defn apply-rule [rule street]
;  (mlet [ '[?ante :=> ?consq] rule]
;        (mif [(? ante) street]
;             (mout (? consq))
;             )))
;
;(defn serve-lmg [state]
;  (for [rule rules]
;    (apply-rule rule state))
;    )
;
;
;(def rules
;  '(; cook moves
;     (([john street ?x]) :=> ([john street  ?x]))
;     ))

;(defn lookup [reln  val tuples]
;  (mfor [[reln '?x val] tuples]
;        (? x)))

;(defmatch apply-rule [facts]
;  (mlet [ '[?ante :=> ?consq] facts]
;        (mif [(? ante) facts]
;             (mout (? consq))
;             )))

;(defmatch apply-rule [facts]
;          ((rule ?n ??antecedents :=> ??consequents)
;            :=> (mfor* [(? antecedents) facts]
;                      (mout(? consequents)))))
;
;(defn serve-lmg [rules facts]
;  (let [new-facts (apply-rule facts)]
;    (if (clojure.set/subset? new-facts facts)
;      facts
;      (recur rules (clojure.set/union facts new-facts)))
;    )
;  )
;
;(defn apply-all [rules facts]
;  (reduce concat
;          (map #(apply-rule % facts) rules)))
;
;(def rules
;           '(; cook moves
;              (rule 0 (junction1 ?x)(street1 ?y) (on ?x ?y) => (junction2 ?y)(street2 ?x))
;
;              (rule 1 (street1 ?x) => (junction1 ?x))
;              (rule 2 (street2 ?x) => (junction2 ?x))
;              )
;  )

;________________________________________________________________________________________________

(def facts1
  '((on street1 junction1)
     (on street2 junction1)
     (on street2 junction2)
     (on street3 junction2)
     (on street3 junction6)
     (on street1 junction6)
     (on street1 junction5)
     (on street2 junction5)
     (on street2 junction3)
     (on street3 junction3)
     (on street3 junction4)
     (on street1 junction4)
     (on john junction1)
         ))

(def facts2
  '((on street1 junction1)
     (on street2 junction1)
     (on street2 junction2)
     (on street3 junction2)
     (on street1 junction3)
     (on street3 junction3)
     (on street2 junction4)
     (on street4 junction4)
     (on john junction1)
         ))

(def facts3
  '( (on john junction3)
     (on street1 junction2)
     (on street2 junction3)
     (on street3 junction4)
     (on street4 junction1)
         ))

;__________________________________________________

(defmatch apply-rule [facts]
          ((rule ?n ??antecedents => ??consequents)
            :=> (mfor* [(? antecedents) facts]
                       (mout (? consequents))))
          )
;__________________________________________________

(def rules1
  '((rule 0 (on john ?y) => (on ?x ?z)
          (rule 1 (on john ?z) => (on ?x ?y))
           )))

;__________________________________________________

(defn apply-all [rules facts]
  (reduce concat
          (map #(apply-rule % facts) rules)
     ))
__________________________________________________
;(defn apply-all [rules facts]
;  (set (reduce concat
;               (map #(apply-rule % facts) rules)
;               )))
;
;(fwd-chain rules1 (set facts3))
;__________________________________________________

(defn fwd-chain [rules facts]
  (let [new-facts (apply-all rules facts)]
    (if (clojure.set/subset? new-facts facts)
          facts
          (recur rules (clojure.set/union facts new-facts))
          )))

;__________________________________________________

(apply-rule rules1 facts3)
(apply-all rules1 facts3)
(fwd-chain rules1 (set facts3))






