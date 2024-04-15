import {Injectable} from "@angular/core";
import {Ambiente} from "../ambiente/Ambiente";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TarefaModelo} from "../modelo/TarefaModelo";

@Injectable({
  providedIn: "root"
})

export class ServicoTarefa {

  private readonly URL = Ambiente.tarefaServiceURL;

  constructor(private http: HttpClient) {
  }

  salvarTarefa (tarefaModelo : TarefaModelo): Observable<TarefaModelo>{
    return this.http.post<TarefaModelo>(this.URL, tarefaModelo);
  }
  retornarTodasAsTarefas (): Observable<TarefaModelo[]>{
    return this.http.get<TarefaModelo[]>(this.URL);
  }

  retornarTarefa (id : string): Observable<TarefaModelo>{
    const url = `${this.URL}/${id}`;
    return this.http.get<TarefaModelo>(this.URL);
  }

  deletarTarefa(id: string): Observable<TarefaModelo> {
    const url = `${this.URL}/${id}`;
    return this.http.delete<TarefaModelo>(url);
  }

  atualizarTarefa (id : string, tarefa: TarefaModelo): Observable<TarefaModelo> {
    const url = `${this.URL}/${id}`;
    return this.http.put<TarefaModelo>(url, tarefa);
  }

}
