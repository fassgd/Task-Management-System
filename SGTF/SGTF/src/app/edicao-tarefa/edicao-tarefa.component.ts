import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {DatePipe, formatDate, NgIf} from "@angular/common";
import {TarefaModelo} from "../modelo/TarefaModelo";
import {ServicoTarefa} from "../servico/ServiçoTarefa";

@Component({
  selector: 'app-edicao-tarefa',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    DatePipe
  ],
  templateUrl: './edicao-tarefa.component.html',
  styleUrl: './edicao-tarefa.component.css'
})
export class EdicaoTarefaComponent implements OnInit {

  id: string = '';
  titulo: string = '';
  descricao: string = '';
  responsavel: string = '';
  prioridade: string = '';
  deadline: Date = new Date();
  situacao: string = '';
  dadosCarregados: boolean = false;

  constructor(private route: ActivatedRoute, private servicoTarefa: ServicoTarefa) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id']
      this.titulo = params['titulo']
      this.descricao = params['descricao']
      this.responsavel = params['responsavel']
      this.prioridade = params['prioridade']
      this.deadline = new Date(params['deadline'])
      this.situacao = params['situacao']
      this.dadosCarregados = true;
    })
  }

  editarTarefa() {
    if (this.titulo == "" || this.descricao == "" || this.responsavel == ""
      || this.prioridade == "" || this.deadline == null || this.situacao == "") {
      alert("Atenção: há campos não preenchidos!");
    } else {
      const tarefa: TarefaModelo = this.criarTarefa();
      console.log(tarefa);
      this.servicoTarefa.atualizarTarefa(this.id, tarefa)
        .subscribe(res => alert("Tarefa atualizada com sucesso! :)"));
      this.limparCampos(this.titulo, this.descricao, this.responsavel, this.prioridade,
        this.deadline, this.situacao);
    }
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

  limparCampos (titulo: string, descricao: string, responsavel: string, prioridade: string,
                deadline: Date, situacao: string){
    this.titulo = '';
    this.descricao = '';
    this.responsavel = '';
    this.prioridade = '';
    this.deadline = new Date();
    this.situacao = '';
  }
}
