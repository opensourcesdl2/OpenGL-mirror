glPushMatrix();
    glScalef(1, -1, 1);  /* "reflect" across Y=0 plane. */
    setLightSourcePositions();
    drawNinja();
  glPopMatrix();
  setLightSourcePositions();
  drawNinja();
  
