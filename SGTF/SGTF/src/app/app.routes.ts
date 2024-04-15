import { Routes } from '@angular/router';
import {ListaTarefasComponent} from "./sgt/lista-tarefas/lista-tarefas.component";
import {DefinicaoTarefaComponent} from "./sgt/definicao-tarefa/definicao-tarefa.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {PaginaInicialComponent} from "./pagina-inicial/pagina-inicial.component";
import {EdicaoTarefaComponent} from "./edicao-tarefa/edicao-tarefa.component";
import {TelaTransicaoComponent} from "./tela-transicao/tela-transicao.component";

export const routes: Routes = [
  {path: '', component: TelaTransicaoComponent},
  {path: 'sgt', component: PaginaInicialComponent},
  {path: 'cadastrartarefa', component: DefinicaoTarefaComponent},
  {path: 'gerenciartarefa', component: ListaTarefasComponent},
  {path: 'editartarefa', component: EdicaoTarefaComponent},
  {path: '**', component: PageNotFoundComponent},
];
