import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {ServicoTarefa} from "../../servico/ServiçoTarefa";
import {TarefaModelo} from "../../modelo/TarefaModelo";
import {DatePipe, NgForOf} from "@angular/common";
import {Router} from "@angular/router";
import {tap} from "rxjs";

@Component({
  selector: 'app-lista-tarefas',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    DatePipe
  ],
  templateUrl: './lista-tarefas.component.html',
  styleUrl: './lista-tarefas.component.css'
})
export class ListaTarefasComponent implements OnInit{

  tarefas: TarefaModelo [] = []
  tarefasFiltradas: TarefaModelo [] = []
  titulo: string = '';
  situacao: string = '';
  responsavel: string = '';
  constructor(private servicoTarefa: ServicoTarefa, private router: Router) {
  }

  ngOnInit(): void {
  }

  listarTarefas() {
    this.servicoTarefa.retornarTodasAsTarefas()
      .subscribe(tarefas => {
        if (this.titulo || this.situacao || this.responsavel) {
          this.tarefas = tarefas.filter(tarefa =>
            (!this.titulo || tarefa.titulo.toLowerCase().includes(this.titulo.toLowerCase())) &&
            (!this.situacao || tarefa.situacao.toLowerCase() === this.situacao.toLowerCase()) &&
            (!this.responsavel || tarefa.responsavel.toLowerCase().includes(this.responsavel.toLowerCase()))
          );
        } else {
          this.tarefas = tarefas;
        }
        this.tarefasFiltradas = [...this.tarefas];
      });
  }


  excluirTarefa (id: string){
    this.servicoTarefa.deletarTarefa(id).pipe(
      tap({
        next: (res) => {
          console.log("Resposta do serviço de exclusão:", res);
          alert("Tarefa excluída com sucesso! :)");
          this.listarTarefas();
        },
        error: (e) => {
          console.error("Erro ao excluir tarefa:", e);
        }
      })
    ).subscribe();
  }

  redirecionar (tarefa: TarefaModelo){
    this.router.navigate(['editartarefa',tarefa]);
  }

  concluirTarefa (tarefa: TarefaModelo){
    if (tarefa.situacao == "Concluída"){
      tarefa.situacao = "Pendente";
      this.servicoTarefa.atualizarTarefa(tarefa.id!, tarefa)
        .subscribe(tarefas => this.listarTarefas());
      alert("Agora, a tarefa se encontra pendente! :)")
    } else {
      tarefa.situacao = "Concluída";
      this.servicoTarefa.atualizarTarefa(tarefa.id!, tarefa)
        .subscribe(tarefas => this.listarTarefas());
      alert("Tarefa concluída com sucesso! :)")
    }
  }

}
