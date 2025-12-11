# Jour 11 – Un monde sans mocks

> _11 décembre 2025 – Un monde sans mocks_

![snippet of the day](img/day11.png)

  ==> **Chargement de mémoire fracturée** <==

Au onzième jour du voyage, tu travailles dans l’Usine des Elfes en orbite, où les jouets sont assignés aux elfes à travers les dimensions.  
La production tourne 24/7, la télémétrie vrombit, les tests sont au vert…

… jusqu’à ce qu’une nouvelle loi soit votée :

> « Le conseil du Pôle Nord a déclaré que les mocks sont bannis pour toujours »

Du jour au lendemain, tous les frameworks de mocking deviennent illégaux.  
Tes tests soigneusement écrits pour `ToyProductionService` sont maintenant de la contrebande.  
Ils décrivent toujours le bon comportement… mais leur implémentation est interdite.

Pour que l’usine continue à tourner, tu vas devoir reconstruire ton filet de sécurité **sans un seul mock**.

---

## Défi — Survivre dans un monde sans mocks

Aujourd’hui, tu pars d’une version “avant” de l’exercice, où tous les tests reposent sur des frameworks de mocking (Mockito, Moq, Jest, etc.).

Ta mission : **réécrire les tests en n’utilisant que des doubles de test faits main**.

Pas de framework de mocking.  
Seulement des fakes, stubs, spies, etc. codés à la main.

---

## Zone de jeu

Pour l’exploration des mémoires fracturées, comme ce ne sont pas **tes** propres souvenirs,  
nous allons dans `/exercises` et les dayNN.

Tu peux ensuite choisir la stack que tu veux utiliser comme **stack principale** :

- `exercises/2024/day11/<your-stack>/`

C’est ton terrain de jeu.

---

## Partage avec la communauté

Sur Discord, tu peux partager :

- Comment tu t’es senti en écrivant des tests sans framework de mocking ?  
- Quels types de doubles de test tu as finalement créés (fake, stub, spy, …) ?  
- Est-ce que le design de ton code de production est devenu plus simple ou plus difficile à utiliser ?

Si tu veux rendre ton travail public, ajoute ton fichier dans :

- `community/solutions/dayNN/` en copiant le template :  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- puis ouvre une pull request.

Dans `journey/your-name.md`, écris une courte entrée pour aujourd’hui :

- Dans quelles situations continueras-tu à utiliser des mocks dans la “vraie vie” ?  
- Où préfères-tu maintenant des doubles écrits à la main ?  
- Est-ce que cela a changé ta manière de penser le couplage entre tests et implémentation ?

**Bienvenue dans le monde sans mocks. Gardons l’usine en marche. 🚀**
