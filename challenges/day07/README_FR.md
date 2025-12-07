# Jour 07 – Bug Driven Development

> **Fragment de mémoire – 2023 · Jour 9**  
> Je me souviens de ce générateur de relevé.  
> Il affichait un joli récapitulatif, et le total avait l’air correct.  
> Du moins la première fois.  
> Mais la fois suivante… le montant avait changé.  
> ...  
> *Peut-être que le bug n’était pas dans les maths, mais dans la façon dont le temps et l’état se sont glissés dans le code.*  

Pour ce septième jour du voyage, nous revisitons la **mémoire du Jour 9 de 2023**.

Le test existant passe… mais le comportement est-il vraiment correct ?

Aujourd’hui, l'approche est différente, nous pratiquons le **Bug Driven Development** :

> Tu n’as **pas le droit de modifier le code de production**  
> tant que tu n’as pas un test en échec qui montre le mauvais comportement.

---

## Défi — Laisser le bug guider le refactoring

Ta mission :

- **Comprendre le comportement actuel**  
- **Imaginer quels comportements pourraient être incorrects**  
- **Écrire des tests qui exposent le ou les bugs**  
- **Corriger le code, un test rouge à la fois**

Laisse les tests en échec te guider sur *où* et *comment* refactorer.

---

## Zone de jeu

Choisis la stack que tu veux utiliser comme **stack principale** cette année et ouvre la mémoire du Jour 9 de 2023 :

- `memories/2023/exercises/<your-stack>/day09`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager par exemple :

- le **premier test en échec** que tu as écrit et le bug qu’il a révélé,  
- un extrait avant / après du design de `Client`,  
- comment ta vision de « où était vraiment le bug » a évolué.

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Quel bug ai-je **délibérément chassé** en premier ?  
- Qu’est-ce que mes **nouveaux tests** disaient du design que je voulais vraiment ?  

Quand la boucle temporelle fait remonter un bug,  
laisse le test être la lanterne qui t’indique comment le corriger. 🐛💡
