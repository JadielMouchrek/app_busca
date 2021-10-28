# PDF Search

O presente trabalho tem como objetivo a análise e a aplicação das técnicas de busca de informações em documentos digitalizados usando a tecnologia OCR. Para isto foi desenvolvida uma aplicação em Java, que utiliza métodos para extrair e comparar dados, aplicando procedimentos de busca de tal forma a auxiliar na gestão documental. Esta tecnologia trabalha com a leitura de caracteres por meio de funções matemáticas aplicadas nos arquivos. Essas funções, contidas na biblioteca Itextpdf, são executadas pela aplicação; após este procedimento, o método de inserção armazena os dados em uma base, e posteriormente o método de comparação atua, comparando estes dados com palavras-chaves digitadas pelo usuário no campo de texto, trazendo assim a localização dos arquivos PDF, que contêm as palavras-chaves fornecidas. A utilização da aplicação proporciona benefícios nas pesquisas de documentos para uso pessoal ou administrativo, assim como segurança de dados, melhor processamento de informações contidas em bancos de dados e eficiência de trabalho. Uma vez que o procedimento de busca é aplicado, obtêm-se os resultados de acordo com os critérios informados com menor consumo de tempo e recursos comparado com o procedimento de busca manual.

# Telas da Aplicação
 [1- Tela de Login](https://imgur.com/V7nnJFA)
 [2- Tela da Aplicação](https://imgur.com/ikmB2AT)
 [3- Diagrama de Entidade de Relacionamento](https://imgur.com/3iKT8xz)

# Iniciando
Essas instruções fornecerão uma cópia do projeto em execução na um máquina local para fins de desenvolvimento e teste. Consulte implantação para obter notas sobre como implantar o projeto.


## Pré-requisitos:
 Para execução e desenvolvimento do projeto é necessário, ou que tenha instalado:
 - NetBeans
 - MySQL WorkBench
 - Pacote Java Development Kit - JDK

## Desenvolvido com
 - Java
 - SQL

## Execução pro projeto
 - A aplicação foi desenvolvida para ser usada no desktop, faça toda instalação e configuração da etapa Pré-Rquisito, no MySQL construa o banco de dados com as respectivas tabelas e campos [3- Diagrama de Entidade de Relacionamento](https://imgur.com/3iKT8xz), após essas configurações, na IDE NetBeans faça a execução da aplicação (obs: apaga os arquivos PDF da lixeira e os arquivos protegidos por senha não serão lidos).

## Arquitetura do projeto	
	
	-imagens
	-nbproject
	-src
		-app_busca
		      -Ajust_tabela.java
		      -Atualiza_JLabel.java
		      -Conecta_usuario.java
		      -Conexao.java
		      -CriaTableModel.java
			  -Exec_cmd.java
			  -Extrai_txt.java
			  -JFixedColScrollPane.java
			  -Lista_dados_principal.java
			  -Lista_principal.java
			  -PDFReader.java

		-nbproject
		-Telas
		    
	
### Stacks
	- A aplicação foi desenvolvida em Java para desktop
	- O banco de dados foi desenvolvido no MySQL WorkBench
	- Parte do projeto onde conterá os fluxos para transição entre as telas
	


 ### Melhorias
 - O projeto do PDF Search ainda sofrerar melhorias como: inclusão do diretório pela tela, testes e migração em servidor, estilização  e desing.

## Autores
- Jadiel Elias Mouchrek dos Santos

## Imagens e SVG (Autores)
    As imagens que utilizei vieram do meu TCC - Ciência da Computação
