import { AbsoluteFill, staticFile } from "remotion";
import { Video } from "@remotion/media";
import { LowerThird } from "./LowerThird";

interface VideoSceneProps {
  src: string;
  message: string;
}

export const VideoScene: React.FC<VideoSceneProps> = ({ src, message }) => {
  return (
    <AbsoluteFill>
      {/* Full-screen video */}
      <Video
        src={staticFile(src)}
        style={{
          width: "100%",
          height: "100%",
          objectFit: "cover",
        }}
      />

      {/* Bottom gradient overlay */}
      <div
        style={{
          position: "absolute",
          bottom: 0,
          left: 0,
          right: 0,
          height: "45%",
          background:
            "linear-gradient(to top, rgba(0,0,0,0.75) 0%, rgba(0,0,0,0) 100%)",
        }}
      />

      {/* Top wordmark */}
      <div
        style={{
          position: "absolute",
          top: 60,
          left: 48,
          display: "flex",
          alignItems: "center",
          gap: 12,
        }}
      >
        <div
          style={{
            backgroundColor: "#f43f5e",
            width: 10,
            height: 40,
            borderRadius: 4,
          }}
        />
        <p
          style={{
            color: "white",
            fontSize: 34,
            fontWeight: 700,
            fontStyle: "italic",
            margin: 0,
            textShadow: "0 2px 8px rgba(0,0,0,0.5)",
          }}
        >
          Ruwi's Cakes
        </p>
      </div>

      {/* Animated lower-third */}
      <LowerThird message={message} />
    </AbsoluteFill>
  );
};
