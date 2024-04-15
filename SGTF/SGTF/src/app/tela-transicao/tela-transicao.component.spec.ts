import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelaTransicaoComponent } from './tela-transicao.component';

describe('TelaTransicaoComponent', () => {
  let component: TelaTransicaoComponent;
  let fixture: ComponentFixture<TelaTransicaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TelaTransicaoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TelaTransicaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
