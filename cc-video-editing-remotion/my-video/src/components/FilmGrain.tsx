import { AbsoluteFill } from "remotion";

export const FilmGrain: React.FC = () => {
  return (
    <AbsoluteFill style={{ pointerEvents: "none" }}>
      <svg style={{ width: "100%", height: "100%" }}>
        <filter id="grain">
          <feTurbulence
            type="fractalNoise"
            baseFrequency="0.85"
            numOctaves="2"
            stitchTiles="stitch"
          />
          <feColorMatrix type="saturate" values="0" />
        </filter>
        <rect
          width="100%"
          height="100%"
          filter="url(#grain)"
          opacity={0.05}
        />
      </svg>
      <AbsoluteFill
        style={{
          background:
            "radial-gradient(ellipse at center, rgba(0,0,0,0) 45%, rgba(0,0,0,0.55) 100%)",
        }}
      />
    </AbsoluteFill>
  );
};
