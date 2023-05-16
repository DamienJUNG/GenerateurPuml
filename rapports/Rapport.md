Rapport Sae21, Damien JUNG et Gaël SEILER
---

## Semaine 1

### Travail fait

Nous avons commencé par faire les 4 premiers points ensemble afin de comprendre et de prendre en main les doclets
ainsi que le fonctionnement des commandes javadoc.

Gaël s'est ensuite chargé de faire les diagrammes de conception et d'analyse, tandis que Damien a écrit le code du pumlDoclet.

### Mode d'emploi

Pour exécuter le programme, il suffit d'appuyer sur le bouton run.
Ce dernier utilisera de lui-même la commande javadoc avec les arguments suivants : 
```
-private -sourcepath ./src -doclet pumlFromJava.PumlDoclet -docletpath /home/jungdamien/Documents/sae/P21/p21Projet/out/production/p21_projet western -d exemples
```
Il est également possible d'utiliser l'argument -out pour spécifier le nom du document.
