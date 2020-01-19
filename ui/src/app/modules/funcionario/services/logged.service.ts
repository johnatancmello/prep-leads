import { Injectable } from '@angular/core';

import { JwtHelperService } from '@auth0/angular-jwt';

import { FuncionarioService } from 'src/app/modules/funcionario/services/funcionario.service';
import { Funcionario } from '../models/funcionarios';
import { ImagemFilter } from '../../pessoa/models/imagem.filter';
import { ImagemService } from '../../pessoa/services/imagem.service';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioLoggedService {

  private funcionario;

  constructor(
    private funcionarioService: FuncionarioService,
    public jwtHelper: JwtHelperService,
    private imagemService: ImagemService
  ) { }

  public getFuncionario(): Promise<Funcionario> {
    return new Promise((resolve, reject) => {
      if (this.funcionario) {
        resolve(this.funcionario);
      } else {

        this.obterFuncionario()
          .then((response) => {
            this.funcionario = response;

            if (this.hasImagem(response)) {
              this.getImagem(response.pessoa.imagem.id)
                .then((imagem) => this.funcionario.pessoa.imagem = imagem)
                .finally(() => resolve(this.funcionario));
            }
          })
          .catch(response => {
            reject(response);
          });
      }
    });
  }


  public getIdFuncionario(): number {
    return this.jwtHelper.decodeToken(this.getTokenLocal()).id;
  }

  public logout() {
    this.funcionario = null;
  }

  private obterFuncionario(): Promise<any> {
    return this.funcionarioService.pesquisarSummaryById(this.getIdFuncionario());
  }

  private getImagem(id: number): Promise<any> {
    return this.imagemService.pesquisarById(id);
  }

  private getTokenLocal(): any {
    return localStorage.getItem('access_token');
  }

  private hasImagem(funcionario: Funcionario): boolean {
    return (funcionario && funcionario.pessoa && funcionario.pessoa.imagem) ? true : false;
  }

}
