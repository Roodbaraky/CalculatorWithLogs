import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpressionFormComponent } from './expression-form.component';

describe('ExpressionFormComponent', () => {
  let component: ExpressionFormComponent;
  let fixture: ComponentFixture<ExpressionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpressionFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpressionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
