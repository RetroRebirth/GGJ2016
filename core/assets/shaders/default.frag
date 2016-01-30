#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif

varying vec2 v_texCoords;
varying LOWP vec4 v_color;

uniform sampler2D u_texture;

void main(void) {
   vec4 texCol =  v_color * texture2D(u_texture, v_texCoords);
   gl_FragColor = vec4(vec3(1.0)-texCol.rgb, texCol.a);
}