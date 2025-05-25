import {styled, Tooltip, tooltipClasses} from "@mui/material";

export const BootstrapTooltip = styled(({ className, ...props }) => (
  <Tooltip {...props} arrow classes={{ popper: className }} />
))(() => ({
  [`& .${tooltipClasses.arrow}`]: {
    color: "var(--header)",
  },
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: "var(--header)",
  },
}));