const cenario = document.getElementById("cenario");
const estudante = document.getElementById("estudante");

const botaoIniciar = document.getElementById("iniciar");

// dimensões do cenário e do estudante
const larguraCenario = cenario.offsetWidth;
const alturaCenario = cenario.offsetHeight;
const larguraEstudante = estudante.offsetWidth;
const alturaEstudante = estudante.offsetHeight;

const velocidade = 10; 


const calcularPosicaoInicial = () => ({
    horizontal: (larguraCenario - larguraEstudante) / 2,
    vertical: (alturaCenario - alturaEstudante) / 2,
    direcaoHorizontal: 0,
    direcaoVertical: 0
});

// = atualizar a posicao
const atualizarPosicao = (posicao, direcao, limite) =>
    Math.max(0, Math.min(posicao + direcao * velocidade, limite));


const moverEstudante = (estado) => ({
    ...estado,
    horizontal: atualizarPosicao(
        estado.horizontal, estado.direcaoHorizontal, larguraCenario - larguraEstudante
    ),
    Vertical : atualizarPosicao(
        estado.vertical, estado.direcaoVertical, alturaCenario - alturaEstudante
    )

});

    
    // // Checa se o estudante saiu dos limites à esquerda
    // if(posicaoHorizontal < 0 ){
    //     posicaoHorizontal = 0; // Se sim, coloca na borda esquerda
    // } else if (posicaoHorizontal + larguraEstudante > larguraCenario ){ 
    //     posicaoHorizontal = larguraCenario - larguraEstudante; // Borda direita
    // }
                                                                                        // totalmente imperativo, refliz de outra forma
    // // Checa se o estudante saiu dos limites em cima
    // if(posicaoVertical < 0 ){
    //     posicaoVertical = 0; // Se sim, coloca na borda superior
    // } else if (posicaoVertical + alturaEstudante > alturaCenario ){
    //     posicaoVertical = alturaCenario - alturaEstudante; 
    // }

    // Atualiza a posição tela
const atualizarTela = (estado) => {
    estudante.style.left = `${novaPosicaoHorizontal}px`;
    estudante.style.top =  `${novaPosicaoVertical}px`;
};                          

const tiroLivros = (posicaoLeftLivro, posicaoTopLivro) => {                 
    const livro = document.createElement("div");
    livro.className = "livro";
    livro.style.position = "absolute";
    livro.style.width = "10px";     
    livro.style.height = "10px";
    livro.style.background = "red";
    livro.style.left = posicaoLeftLivro + "px";
    livro.style.top = posicaoTopLivro + "px";
    cenario.appendChild(livro);
    return livro;
};                                 
                                            
// Função para mover o livro
const moverLivro = (livro) => {
    const novaPosicaoTop = parseInt(livro.style.top) -5;
    livro.style.top = `${novaPosicaoTop}px`;
        // Cria um novo estado de posição
                                    

// Continua o movimento enquanto o projétil está na tela
if (novaPosicao.top > 0) {  // Condição para manter o movimento
    requestAnimationFrame(() => moverLivro(livro));
} else {
    // Remove o livro quando sai da tela
    livro.remove();
}
};
                                        
    // // Iniciar o movimento
    // moverLivro(livro, { left: posicaoLeftLivro, top: posicaoTopLivro });
    // };
                                        
    // Função que chama tiroLivros
const atirar = (estado) => {
    const livro = criarLivro(estado.horizontal + larguraEstudante / 2, estado.vertical - 10);
    moverLivro(livro);
    return estado;
};

const atualizarDirecao = (estado, tecla, valor) => {
    switch (tecla) {
        case "ArrowRight": return { ...estado, direcaoHorizontal: valor };
        case "ArrowLeft": return { ...estado, direcaoHorizontal: -valor };
        case "ArrowDown": return { ...estado, direcaoVertical: valor };
        case "ArrowUp": return { ...estado, direcaoVertical: -valor };
        default: return estado;
    }
};



const teclaPressionada = (estado, tecla) => atualizarDirecao(estado, tecla, 1);
const teclaNaoPressionada = (estado, tecla) => atualizarDirecao(estado, tecla, 0);



const iniciarJogo = () => {

    let estado = calcularPosicaoInicial();

    const loop = () => {
        estado = moverEstudante(estado);
        atualizarTela(estado);
        requestAnimationFrame(loop);
    };
    loop(); 

    document.addEventListener("keydown", (event) => {
        estado = teclaPressionada(estado, event.key);
        if (event.key === " ") estado = atirar(estado);
    });

    document.addEventListener("keyup", (event) => {
        estado = teclaNaoPressionada(estado, event.key);
    });

    botaoInciar.style.display = "none"; // Oculta o botão de iniciar
    // let checalivros = setInterval(atirar, 10);
};
botaoIniciar.addEventListener("click", iniciarJogo);


// PROBLEMA ATUAL, NAO ESTA ATIRANDO E NEM INICIANDO 