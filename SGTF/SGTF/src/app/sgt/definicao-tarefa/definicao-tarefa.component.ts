import {Component, OnInit} from '@angular/core';
import {TarefaModelo} from "../../modelo/TarefaModelo";
import {ServicoTarefa} from "../../servico/ServiçoTarefa";
import {FormsModule} from "@angular/forms";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-definicao-tarefa',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './definicao-tarefa.component.html',
  styleUrl: './definicao-tarefa.component.css'
})
export class DefinicaoTarefaComponent implements OnInit{

  titulo: string = '';
  descricao: string = '';
  responsavel: string = '';
  prioridade: string = '';
  deadline: Date = new Date();
  situacao: string = '';

  constructor(private servicoTarefa : ServicoTarefa) {
  }

  ngOnInit(): void {
  }

  salvarTarefa (){
    if (this.titulo == "" || this.descricao == "" || this.responsavel == ""
      || this.prioridade == "" || this.deadline == null || this.situacao == ""){
      alert("Atenção: há campos não preenchidos!");
    } else {
      const t : TarefaModelo = this.criarTarefa();
      this.servicoTarefa.salvarTarefa(t)
        .subscribe(res=> alert("Tarefa cadastrada com sucesso! :)"));
      this.limparCampos(this.titulo, this.descricao, this.responsavel, this.prioridade,
        this.deadline, this.situacao);
    }
  }

  limparCampos (titulo: string, descricao: string, responsavel: string, prioridade: string,
                deadline: Date, situacao: string){
    this.titulo = '';
    this.descricao = '';
    this.responsavel = '';
    this.prioridade = '';
    this.deadline = new Date();
    this.situacao = '';
  }

  criarTarefa (): any{
    const t : TarefaModelo = {
      titulo: this.titulo,
      descricao: this.descricao,
      responsavel: this.responsavel,
      prioridade: this.prioridade,
      deadline: formatDate(this.deadline, 'yyyy-MM-dd', 'en-US'),
      situacao: this.situacao
    }
    return t;
  }
}
