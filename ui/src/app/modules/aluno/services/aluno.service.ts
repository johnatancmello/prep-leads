import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';

import { Aluno } from '../models/aluno';
import {SecutiryHttpService} from '../../security/services/http.service';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  private location = '/alunos';

  constructor(private http: SecutiryHttpService) { }

  adicionar(aluno: Aluno): Promise<any> {
    return this.http.adicionar(aluno, this.location);
  }

  alterar(aluno: Aluno): Promise<any> {
    return this.http.alterar(aluno, this.location);
  }

  excluir(id: number): Promise<void> {
    return this.http.excluir(id, this.location);
  }

  pesquisar(params: HttpParams): Promise<any> {
    return this.http.pesquisar(params, this.location);
  }

  pesquisarById(id: number): Promise<any> {
    return this.http.pesquisar(null, this.location + '/' + id);
  }
}
