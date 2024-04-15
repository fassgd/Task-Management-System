import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefinicaoTarefaComponent } from './definicao-tarefa.component';

describe('DefinicaoTarefaComponent', () => {
  let component: DefinicaoTarefaComponent;
  let fixture: ComponentFixture<DefinicaoTarefaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefinicaoTarefaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DefinicaoTarefaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
