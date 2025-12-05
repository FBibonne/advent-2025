# Jour 05 – Bataille de TDD, Humain vs IA

![extrait du jour](img/day05.png)

> **Fragment de mémoire – 2024 · Jour 5**  
> Je me souviens de ces identifiants d’elfes.  
> Des lignes de chiffres, des règles griffonnées sur une feuille,  
> et une liste de tests qui s’allongeait dans la marge.  
> Je pensais avoir tout couvert…  
> jusqu’à ce que quelqu’un (ou quelque chose) demande :  
> « Et *ce* cas-là ? »  
> ...  
> *Peut-être que je n’étais pas le seul à écrire des tests ce jour-là.*

Pour ce cinquième jour de l'aventure, nous revisitons la [mémoire](https://github.com/advent-of-craft/2024/blob/main/docs/exercise/day05/challenge.md) du **validateur d’EID** de l’édition 2024.

La dernière fois, l’objectif était de concevoir ce validateur en TDD.

Cette fois, le twist est différent :

> Toi et un **assistant IA** allez chacun proposer une liste de tests  
> et tu décideras laquelle tu choisis.

Tu peux aussi combiner les deux.

---

## Défi — Utilise les deux cerveaux (Humain & IA) pour implémenter le validateur.

Le défi du jour se déroule en quatre phases :

1. **Comprendre les règles métier**  
2. **Écrire ta propre liste de tests (Humain)**  
3. **Demander une liste de tests à une IA (IA)**  
4. **Choisir, ajuster et implémenter en TDD**

Tu peux faire tout l’exercice, ou choisir le **mode facile** en laissant de côté la clé de contrôle.  
Un sous-ensemble ciblé, guidé par de bons tests, est largement suffisant.

---

## Rappel des règles EID

D’après la mémoire 2024, un EID (Elf Identifier) comporte 8 chiffres :

| Positions | Signification      | Valeurs possibles                                   |
|----------|--------------------|----------------------------------------------------|
| 1        | Sexe               | `1` Sloubi, `2` Gagna, `3` Catact                  |
| 2–3      | Année de naissance | deux derniers chiffres, de `00` à `99`            |
| 4–6      | Numéro de série    | ordre de naissance, de `001` à `999`              |
| 7–8      | Clé de contrôle    | `97 - (les 6 premiers chiffres mod 97)`, de `01` à `97` |

But : concevoir un validateur (ou un cœur de validation) qui applique ces règles.

---

## Se rappeler du flux TDD

Souviens-toi du cycle TDD :

1. Écrire le test en premier (ou le modifier) → il doit **échouer** (rouge)  
2. Écrire le code le plus simple pour le faire passer → **vert**  
3. Refactorer en restant vert  

💡 Astuce : laisse l’exécution des tests te guider vers ce qu’il faut faire ensuite.

Laisse les meilleures idées gagner, pas le cerveau qui crie le plus fort.

---

## Zone de jeu

Choisis la stack que tu veux utiliser comme **stack principale** cette année et ouvre la mémoire du Jour 5 de 2024 :

- `memories/2024/exercises/<your-stack>/day05`

C’est ton terrain de jeu pour le validateur d’EID.

Si tu manques de temps, tu peux choisir le mode facile :

- commence par te limiter au **format + sexe + série**,  
- ajoute des tests sur la clé de contrôle uniquement si tu as encore de la marge.

Laisse tes tests montrer le chemin ➰💫

---

## Partage avec la communauté

Partage tes découvertes sur Discord et récolte des retours de la communauté.  
Partage ta liste de tests et explique pourquoi tu as fusionné / ajusté ou écarté certains éléments.

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- À quoi ressemblait ma **liste de tests** avant l’IA ?  
- En quoi l’IA m’a aidé ou surpris ?  
- Quels tests ai-je gardés au final, et pourquoi ?  
- Est-ce que partir d’une liste de tests “curatée” a changé ma façon de vivre le TDD ?

Ce n’est pas une question de savoir qui gagne mais de comment cela à ajuster notre façon de penser. 🧠🤖
