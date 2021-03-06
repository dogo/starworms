"""
gerador_mapas.py - Gera mapas para os jogos feitos no projeto integrado do terceiro semestre 
pelos alunos do curso de BCC do Centro Universitario SENAC SP.

Adaptado e incluso algumas melhorias para Starworms.

Voce precisa da biblioteca eagle para rodar este programa (e do PyGTK).

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA


Last time updated: 29/01/2007

Changelog :

v0.1
(Bruno Gola - 26/01/2007):
Erro na verificao dos players no mapa.

(Bruno Gola - 26/01/2007):
Cor do retangulo errada na hora de colocar no array.

(Diogo Autilio - 26/01/2007):
Faltava o campo "Turno"

(Diogo Autilio - 26/01/2007):
Estava gravando errado a posicao dos players 

(Diogo Autilio - 26/01/2007):
Adicionado botao "Fechar" 

v0.2
(Diogo Autilio - 29/01/2007):
Tamanho do projetil estava errado.

(Bruno Gola - 29/01/2007):
Inserida uma dialog onde o usuario escolhe a altura e largura antes de desenhar o cenario.

v0.2.1
(Diogo Autilio - 11/01/2008):
Adicionado campo "playerspeed", usado para os pulos.

Features :

* Usuario podera escolher o tamanho do obstaculo.

Created by Bruno Fialho Marques Gola 
Send any comments, bugs, fixes, requests and money to < brunogola@gmail.com >.
Send any comments, bugs, fixes to < diogo.autilio@gmail.com >.

"""


import sys

# eagle:
from eagle import *

# Globais:
#global TELA_W
#global TELA_H
TELA_W = 0
TELA_H = 0
# fim 

def set_altura(app,wid,alt):
    global TELA_H
    TELA_H=alt

def set_largura(app,wid,larg):
    global TELA_W
    TELA_W=larg

def ok(app,wid):
    global TELA_W,TELA_H
    try:
        TELA_W = int(TELA_W)
        TELA_H = int(TELA_H)
    except ValueError:
        print "ERRO: o(s) valor(s) inserido(s) nao eh/sao inteiro(s)."
        sys.exit()
    app.close()

App(
    title="Digite a altura e a largura da tela :",
    window_size=(400,125),
    center=(
        Entry(id='altura',label="Altura: ",callback=set_altura),
        Entry(id='largura',label='Largura: ', callback=set_largura),
        Button(id='ok',label='Ok',callback=ok),
    ),
)

run()

if not TELA_W or not TELA_H:
    sys.exit()
# Definindo tamanho dos objetos
TAMANHO = 30

# Cabecalho do arquivo de mapa
headers =[
        ["tela",str(TELA_W),str(TELA_H)],
        ["playersize","55","55"],
        ["playerspeed","40","45"],
        ["hitpoints","5"],
        ["player1"],
        ["player2"],
		["turno","45"],
        ["projetil","40",str(TAMANHO)]
]
        
rects = []
global p1, p2


def intersects(r1,r2):
    """ 
    Verifica se o retangulo r2 esta em cima do retangulo r1.
    """
    if (r2[0] >= r1[0] and r2[0] <= r1[0]+TAMANHO and r2[1] >= r1[1] and r2[1] <= r1[1]+TAMANHO):
        return True
    if (r2[0]+TAMANHO >= r1[0] and r2[0] <= r1[0] and r2[1]+TAMANHO >= r1[1] and r2[1] <= r1[1]):
        return True
    if (r2[0]+TAMANHO >= r1[0] and r2[0] <= r1[0] and r2[1] >= r1[1] and r2[1] <= r1[1]+TAMANHO):
        return True
    if (r2[0] >= r1[0] and r2[0] <= r1[0]+TAMANHO and r2[1]+TAMANHO >= r1[1] and r2[1] <= r1[1]):
        return True
    return False

def mouse_callback( app, wid, button, x, y ):
    if button == 2:
        for i in rects:
            if intersects([int(i[0]),int(i[1])],[x,y]):
                return
        app["canvas"].draw_rectangle(x,y,TAMANHO*2,TAMANHO*2,color=color_chooser.cor_atual,fillcolor=color_chooser.cor_atual,filled=True)
        cor_hexa = '#'
        print color_chooser.cor_atual
        for i in range(len(color_chooser.cor_atual)):
            aux=hex(color_chooser.cor_atual[i])[2:]
            if len(aux) == 1:
                aux = "0"+aux
            cor_hexa += aux 
        rects.append([str(x),str(y),str(20),cor_hexa])
        print cor_hexa
    elif button == 1:
        if mouse_callback.p1 or not seleciona_p1.img:
            return
        img = Image( filename=(seleciona_p1.img))
        app["canvas"].draw_image(image=img,x=x,y=y)
        headers[3].append(str(x))
        headers[3].append(str(y))
        mouse_callback.p1 = True

    elif button == 4:
        if mouse_callback.p2 or not seleciona_p2.img:
            return
        img = Image( filename=(seleciona_p2.img))
        app["canvas"].draw_image(image=img,x=x,y=y)
        mouse_callback.p2 = True
        headers[4].append(str(x))
        headers[4].append(str(y))
        mouse_callback.p2 = True

mouse_callback.p1 = False
mouse_callback.p2 = False

def color_chooser(app,wid,valor):
    color_chooser.cor_atual = valor

def seleciona_p1(app,wid,img):
    seleciona_p1.img = img

def seleciona_p2(app,wid,img):
    seleciona_p2.img = img

def gera_mapa(app,wid):
    print (mouse_callback.p1,mouse_callback.p2)
    if not mouse_callback.p1 or not mouse_callback.p2:
        if not yesno("Voce nao colocou todos os personagens,\n se salvar agora, o mapa nao ira funcionar.\n Deseja prosseguir?"):
            return
    file = app.file_chooser(FileChooser.ACTION_SAVE)
    if file:
        file = open(file,'w')
        for i in headers:
            for j in i:
                file.write(j+"\t")
            file.write("\n")
        for i in rects:
            file.write("rect\t")
            for j in i:
                file.write(j+"\t")
            file.write("\n")
        file.close()
    else:
        pass

def apaga_tudo(app, wid):
    app["canvas"].clear()
    mouse_callback.p1 = False
    mouse_callback.p2 = False
    print rects
    for i in rects:
        rects.pop()

# Botao Fechar
def fechar(app, wid):
	quit();

    
color_chooser.cor_atual = (255,0,0)

App(
    title="Gerador de mapas para starWorms v0.2.1",
    top = (
        RichText(id="text",
            text="Selecione a imagem dos players e desenhe o mapa no Canvas abaixo.<br><b>Mouse 1</b>: Insere o Player1<br><b>Mouse 2</b>: Insere o Player2<br><b>Botao do meio</b>: Insere um Obstaculo"
        )
    ),
    center=(
        Canvas( id="canvas",
            width=TELA_W, height=TELA_H,
            callback=mouse_callback,
            bgcolor='blue'
        ),
        OpenFileButton(id="p1_selector",callback=seleciona_p1),
        OpenFileButton(id="p2_selector",callback=seleciona_p2),
        Color(id="color_selector",callback=color_chooser,color='red'),
        Button(id="ok",label="Gerar Arquivo de Cenario",callback=gera_mapa),
        Button(id="apaga",label="Apagar cenario",callback=apaga_tudo),
	Button(id="fechar",label="Fechar",callback=fechar),
    )
        
)

run()
