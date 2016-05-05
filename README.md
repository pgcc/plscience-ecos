plscience-ecos
==============
1. Criar projeto

  * Clonar ecospl-science no netbeans: Equipe -> Git -> Clonar OU Equipe -> Remoto -> Clonar

  * URL:  https://github.com/pgcc/plscience-ecos.git

==============
2. Adicionar o projeto no netbeans:

  * Clicar em Novo-> Projeto Web com códigos-fontes existentes

  * Selecionar localização do projeto e colocar plscience-ecos como nome do projeto

  * Clicar em próximo

  * Selecionar servidor TomCat -> Próximo

  * Clicar em finalizar

Projeto é criado, mas aparece erro por falta de bibliotecas

==============
3. Adicionar bibliotecas:

  * Adicionar todas as bibliotecas uma a uma, clicando com o botão direito em bibliotecas -> Adicionar JAR/Pasta

  * As bibliotecas estão contidas na pasta libs (.../plscience-ecos/libs).

==============
4. Utilização do banco de dados do E-SECO. Etapas:

  4.1- Ter o MySQL e o MySQL Workbench instalados. O MySQL Workbench é importante para abrir o modelo do Banco de Dados (o arquivo .mwb).

  4.2- Criar o Banco de Dados com o nome "plscience".

  4.3- Abrir o modelo do Banco de Dados (plscienceprov.mwb) no MySQL Workbench.

  4.4- Mandar sincronizar o modelo com o Banco de Dados (Database -> Synchronize Model).

  4.5- Aparecerá uma tela guia, apertar "next" seguidamente, em uma das telas ele mostrará o Banco de Dados com o qual ele irá sincronizar o modelo, verifique se o Banco de Dados é o "plscience".

  4.6- Depois disso rode o script, se tudo der certo, não vai dar erro. =)

==============
5. Popular o banco (se necessário):

plscience-ecos/web/files/db_script -> arquivo plscience-insert.sql
==============

Problema da fonte de dados (Pode ocorrer, mas aparentemente aparece apenas no Linux.)

Se aparecer um alerta sobre o nome do projeto no netbeans, clicar com o botão direito -> Resolver problemas da fonte de dados...
